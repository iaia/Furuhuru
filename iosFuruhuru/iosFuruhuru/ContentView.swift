import SwiftUI
import core

struct ContentView: View {
    
    let applicationInfo = ApplicationInfo("furuhuru-ios-example", "1.2.3")
    
    let greet = IssueBodyBuilder.build(
        "username",
        "usersBody"
        "imageUrl"
        "fileUrl",
        applicationInfo
    )
    
    var body: some View {
        Text(greet)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
