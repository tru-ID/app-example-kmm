//
//  APIManager.swift
//  PhoneCheckiOSApp
//
//  Created by Didem Yakici on 05/04/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

final class APIManager {
    
    private var serverUrl: String 
    
    public init(url: String) {
        self.serverUrl = url
    }
    
    enum CheckError: Error{
        case badRequest
        case internalError
    }
    
    func postCheck(withPhoneNumber phone:String, completionHandler: @escaping(Result<Check,CheckError>)  -> Void) {
        let json: [String:Any] = ["phone_number": phone]
        let jsonData = try? JSONSerialization.data(withJSONObject: json)
        
        let config = URLSessionConfiguration.ephemeral
        config.waitsForConnectivity = true
        config.timeoutIntervalForResource = 300
        config.allowsExpensiveNetworkAccess = true
        config.allowsCellularAccess = true
        let session = URLSession(configuration: config)
        print("serverUrl: \(serverUrl)")
        let endPoint: String = serverUrl + "/check"
        print("postCheck: \(endPoint)")
        
        let url = URL(string: endPoint)!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.httpBody = jsonData
        request.setValue("application/json", forHTTPHeaderField: "content-type")
        print("request " + request.description)
        
        let task = session.dataTask(with: request) { (data, response, error) in
            if let error = error {
                print("Error returning phone \(phone): \(error)")
                return
            }
            if let httpResponse = response as? HTTPURLResponse {
                print("response status code: \(httpResponse.statusCode)")
                if (!(200...299).contains(httpResponse.statusCode)) {
                    if ((400...499).contains(httpResponse.statusCode)) {
                        completionHandler(.failure(.badRequest))
                    }
                    if (httpResponse.statusCode >= 500) {
                        completionHandler(.failure(.internalError))
                    }
                    return
                }
            }
            if let data = data,
                let check = try? JSONDecoder().decode(Check.self, from: data) {
                    completionHandler(.success(check))
                }
            }
        task.resume()
            
        }
        
    func getCheckStatus(withCheckId id: String, completionHandler: @escaping (CheckStatus) -> Void) {
        let config = URLSessionConfiguration.default
        config.waitsForConnectivity = true
        config.timeoutIntervalForResource = 300
        let session = URLSession(configuration: config)
        let endPoint: String = serverUrl + "/check_status?check_id=\(id)"
        print("getCheckStatus: \(endPoint)")
        if let url = URL(string: endPoint) {
            print(url)
            
            var request = URLRequest(url: url)
            request.httpMethod = "GET"
            let task = session.dataTask(with: request) { (data, response, error) in
                if let error = error {
                    print("Error returning id \(id): \(error)")
                    return
                }
                guard let httpResponse = response as? HTTPURLResponse,
                      (200...299).contains(httpResponse.statusCode) else {
                          print("Unexpected response status code: \(response)")
                          return
                      }
                if let data = data,
                   let check = try? JSONDecoder().decode(CheckStatus.self, from: data) {
                    completionHandler(check)
                }
            }
            task.resume()
        } else {
            print("error")
        }
    }
    
    struct Check: Codable {
      let id: String
      let url: String
      
      enum CodingKeys: String, CodingKey {
        case id = "check_id"
        case url = "check_url"
      }
      
      init(id: String,
           url: String) {
        self.id = id
        self.url = url
      }
    }
    
    struct CheckStatus: Codable {
      let id: String
      let match: Bool

      enum CodingKeys: String, CodingKey {
        case id = "check_id"
        case match
      }

      init(id: String, match: Bool) {
        self.id = id
        self.match = match
      }
    }

    
    
}
