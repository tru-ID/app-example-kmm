import SwiftUI
import shared
import tru_sdk_ios

struct ContentView: View {
    @State var phoneNumber = ""
    @State var isEditing: Bool = false
    @State var termsAndConditionsSwitch: Bool = false
    @State var verifyButton: Bool = false
    @State var isLoading: Bool = false
    @State var check: PhoneCheck?
    @State var checkStatus: PhoneCheckResult?
    @State var endResult = ""
    var resultTrue = "\u{2705}"
    var resultFalse = "\u{274C}"
    var phoneNumberTest = NSPredicate(format: "SELF MATCHES %@", "^[0-9+]{0,1}+[0-9]{5,16}$")
    
    let truSDK = ObjcTruSDK()
    
    var body: some View {
        VStack {
            Image("Logo")
                .resizable()
                .scaledToFit()
            
            Text("To experience tru.ID verification, please enter your phone number")
                .font(.body)
                .multilineTextAlignment(.center)
                .padding()
            
            TextField("Phone number", text: $phoneNumber)
                .textFieldStyle(DefaultTextFieldStyle())
                .keyboardType(.phonePad)
                .multilineTextAlignment(.center)
                .font(.title)
            
            HStack {
                Toggle("switch", isOn: $termsAndConditionsSwitch)
                    .toggleStyle(SwitchToggleStyle(tint: .green))
                    .labelsHidden()
                Text("I agree with tru.ID terms and privacy policy")
            }
            .padding()
            .multilineTextAlignment(.center)
            
            Button ("Verify my phone number") {
                let _ = self.truSDK.isReachable { reachabilityDetails, error in
                    print("Reachability Details \(reachabilityDetails) Reachability Error \(error)")
                    print("[isReachable] Done")
                }
                doPhoneCheck(phoneNumber: phoneNumber)
                isLoading.toggle()
                hideKeyboard()
                
            }
            .padding()
            .font(.title2)
            .background(Color.blue)
            .foregroundColor(Color.white)
            .disabled(termsAndConditionsSwitch == false || phoneNumber.isEmpty || !phoneNumberTest.evaluate(with: phoneNumber))
            
            
            if self.isLoading {
                ProgressView("")
                    .progressViewStyle(CircularProgressViewStyle())
                    .font(.largeTitle)
            }
            if verifyButton {
                Text(self.endResult)
                    .font(.largeTitle)
            }
            Spacer()
            
        }.padding()
    }
    
    
    
    func doPhoneCheck(phoneNumber: String) {
        var url = Bundle.main.object(forInfoDictionaryKey: "appServerUrl") as! String
        let apiService = APIService()//Potentially we get the URL from the App and can pass the URL here
        let phoneCheckPost = PhoneCheckPost(phone_number: phoneNumber)
        // Step 1: Send phone number to Server
        apiService.getPhoneCheck(user: phoneCheckPost) { phoneCheck, error in
            if let check = phoneCheck {
                DispatchQueue.main.async {
                    self.check = check
                    let _ = print("1 - App: checkUrlWithResponseBody will be called")
                    // Step 2: Open check_url over cellular
                    self.truSDK.checkUrlWithResponseBody(url: URL(string: check.check_url)!) { body, error in
                        print("Last - App: checkUrlWithResponseBody:: Swift closure call with \(body) - \(error)")
                        // Step 3: Get Result from Server
                        apiService.getPhoneCheckResult(checkId: check.check_id) { result, error in
                            DispatchQueue.main.async {
                                //Rollback the UI state with the updated results.
                                verifyButton.toggle()
                                isLoading.toggle()

                                if let error = error {
                                    endResult = resultFalse
                                    print("\u{274C} \(error)")
                                } else if let result = result {
                                    self.checkStatus = result
                                    if (result.match) {
                                        endResult = resultTrue
                                        print("\u{2705}")
                                    } else {
                                        endResult = resultFalse
                                        print("\u{274C}")
                                    }
                                }
                                
                            }
                        }
                    }
                }
            } else if let error = error {
                DispatchQueue.main.async {
                    verifyButton.toggle()
                    isLoading.toggle()
                    endResult = resultFalse + "error" + " \(error)"
                    print("\u{274C} error \(String(describing: error))")
                }
            }
        }
        
//        let apiManager = APIManager(url: url)
//        apiManager.postCheck(withPhoneNumber: phoneNumber) { (result) in
//
//            
//        }//postCheck closure
    }//doPhoneCheck
    
}


extension View {
    func hideKeyboard() {
        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
