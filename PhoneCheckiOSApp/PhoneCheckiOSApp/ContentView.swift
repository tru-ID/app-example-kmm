import SwiftUI
import shared

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
            
            var platform = Platform()
            var isReachable = platform.isReachable()
            var kIsReachable = try? platform.isReachable()
            let _ = print("isReachable called in iOS")
           
            Spacer()
            
        }.padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
