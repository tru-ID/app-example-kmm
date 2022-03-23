import SwiftUI
import shared


struct ContentView: View {

	var body: some View {
		Text("Hello ")
        var isReachable = Platform().isReachable()
        let _ = print("isReachable called in iOS")
	}
    
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
