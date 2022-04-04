import SwiftUI
import shared
import playground

struct ContentView: View {
    @State var phoneNumber = ""
    @State var isEditing: Bool = false
    @State var termsAndConditionsSwitch: Bool = false
    @State var verifyButton: Bool = false
    @State var isLoading: Bool = false
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
                //.keyboardType(.phonePad)
                .multilineTextAlignment(.center)
                .font(.title)
            
//            let _ = print(phoneNumber)
            HStack {
                Toggle("switch", isOn: $termsAndConditionsSwitch)
                    .toggleStyle(SwitchToggleStyle(tint: .green))
                    .labelsHidden()
                Text("I agree with tru.ID terms and privacy policy")
            }
            .padding()
            .multilineTextAlignment(.center)

            Button ("Verify my phone number") {
                //phonecheck to be performed
                //progressView and result states to be managed
                verifyButton.toggle()
                isLoading.toggle()
            }
            .padding()
            .font(.title2)
            .background(Color.blue)
            .foregroundColor(Color.white)
            
           
            if self.isLoading {
                ProgressView("")
                    .progressViewStyle(CircularProgressViewStyle())
                    .font(.largeTitle)
            }
            if verifyButton {
                Text("result")
                    .font(.largeTitle)
            }
            
            let platform = Platform()
            
            let _ = print("1 - App: checkUrlWithResponseBody will be called")
            let _ = platform.checkUrlWithResponseBody(url: "http://www.cnn.com") { responseDict, error in
                print("Last - App: checkUrlWithResponseBody:: Swift closure call with \(responseDict) - \(error)")
            }
            
//            let _ = platform.checkWithTrace(url: "http://www.cnn.com") { traceInfo, error in
//                print("checkWithTrace:: Swift closure call with \(traceInfo) - \(error)")
//            }
            
//            do{
//
//            } catch {
//
//            }
            //The problem is that the second parameter for the error only "catches" cancellation exceptions, all other errors thrown from the coroutine will need to be caught.
//            let _ = platform.isReachable { reachabilityDetails, error in
//                print("isReachable:: Swift closure call with \(reachabilityDetails) - \(error)")
//            }
//
//
//            let _ = platform.isReachableWithDataResidency(dataResidency: "EU") { reachabilityDetails, error in
//                print("isReachableWithDataResidency:: Swift closure call with \(reachabilityDetails) - \(error)")
//            }
           
            Spacer()
            
        }.padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
