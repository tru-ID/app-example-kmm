import SwiftUI
import shared
import playground

struct ContentView: View {
    @State var phoneNumber = ""
    @State var isEditing: Bool = false
    @State var termsAndConditionsSwitch: Bool = false
    @State var verifyButton: Bool = false
    @State var isLoading: Bool = false
    @State var check: APIManager.Check?
    @State var checkStatus: APIManager.CheckStatus?
    @State var endResult = ""
    var resultTrue = "\u{2705}"
    var resultFalse = "\u{274C}"
    var phoneNumberTest = NSPredicate(format: "SELF MATCHES %@", "^[0-9+]{0,1}+[0-9]{5,16}$")
    
    let platform = Platform()
    
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
                let _ = self.platform.isReachable { reachabilityDetails, error in
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
        var apiManager = APIManager(url: url)
        apiManager.postCheck(withPhoneNumber: phoneNumber) { (result) in
            switch result {
            case .success(let c):
                DispatchQueue.main.async {
                    self.check = c // Should any state variable concerning UI be modified in Main Thread/Queue?
                    let _ = print("1 - App: checkUrlWithResponseBody will be called")
                    self.platform.checkUrlWithResponseBody(url: self.check!.url) { body, error in
                        print("Last - App: checkUrlWithResponseBody:: Swift closure call with \(body) - \(error)")
                        apiManager.getCheckStatus(withCheckId: self.check!.id) { (s) in
                            DispatchQueue.main.async {
                                //Rollback the UI state with the updated results.
                                verifyButton.toggle()
                                isLoading.toggle()
                                self.checkStatus = s
                                if (s.match) {
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
            case .failure(let status):
                DispatchQueue.main.async {
                    verifyButton.toggle()
                    isLoading.toggle()
                    if (status == APIManager.CheckError.badRequest) {
                        endResult = resultFalse + "wrong format"
                        print("\u{274C} wrong format")
                    } else {
                        endResult = resultFalse + "error"
                        print("\u{274C} error")
                    }
                }
                
            }
            
        }
    }
    
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
