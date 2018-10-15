/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

//
//  MainViewController.h
//  SmartGA
//
//  Created by ___FULLUSERNAME___ on ___DATE___.
//  Copyright ___ORGANIZATIONNAME___ ___YEAR___. All rights reserved.
//

#import "MainViewController.h"
#import "UIColor+SGExtention.h"

@implementation MainViewController

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Uncomment to override the CDVCommandDelegateImpl used
        // _commandDelegate = [[MainCommandDelegate alloc] initWithViewController:self];
        // Uncomment to override the CDVCommandQueue used
        // _commandQueue = [[MainCommandQueue alloc] initWithViewController:self];
    }
    return self;
}

- (id)init
{
    self = [super init];
    if (self) {
        // Uncomment to override the CDVCommandDelegateImpl used
        // _commandDelegate = [[MainCommandDelegate alloc] initWithViewController:self];
        // Uncomment to override the CDVCommandQueue used
        // _commandQueue = [[MainCommandQueue alloc] initWithViewController:self];
    }
    return self;
}

- (void)didReceiveMemoryWarning
{
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];

    // Release any cached data, images, etc that aren't in use.
}

#pragma mark View lifecycle

- (void)viewWillAppear:(BOOL)animated
{
    // View defaults to full size.  If you want to customize the view's size, or its subviews (e.g. webView),
    // you can do so here.

    [super viewWillAppear:animated];
    [self requestCheckAppVersion];
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor sg_c1Color];
    // Do any additional setup after loading the view from its nib.
}
    
- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}
    
#pragma mark - app update logic
- (void)requestCheckAppVersion
{
    NSLog(@"앱 버전 체크 시작!!!!!!!");
    NSDictionary *bundleInfo = [[NSBundle mainBundle] infoDictionary];
    NSString *bundleIdentifier = [bundleInfo valueForKey:@"CFBundleIdentifier"];
    NSURL *lookupURL = [NSURL URLWithString:[NSString stringWithFormat:@"http://itunes.apple.com/lookup?bundleId=%@", bundleIdentifier]];
    NSData *lookupResults = [NSData dataWithContentsOfURL:lookupURL];
    NSDictionary *jsonResults = [NSJSONSerialization JSONObjectWithData:lookupResults options:0 error:nil];

    NSUInteger resultCont = [[jsonResults objectForKey:@"resultCount"] integerValue];
    if (resultCont) {
        NSDictionary *appDetails = [[jsonResults objectForKey:@"results"] firstObject];
        NSString *latestVersion = [appDetails objectForKey:@"version"];
        NSString *currentVersion = [bundleInfo objectForKey:@"CFBundleShortVersionString"];
        NSLog(@"latestVersion==============%@", latestVersion);
        NSLog(@"currentVersion=============%@", currentVersion);
        
        NSArray *verTemp1 = [latestVersion componentsSeparatedByString:@"."];
        NSArray *verTemp2 = [currentVersion componentsSeparatedByString:@"."];
        
        NSLog(@"상용 버전 : %@", verTemp1);
        NSLog(@"앱 버전 : %@", verTemp2);
        
        NSInteger realInt0 = [verTemp1[0] integerValue];
        NSInteger realInt1 = [verTemp1[1] integerValue];
        NSInteger realInt2 = [verTemp1[2] integerValue];
        
        NSInteger curInt0 = [verTemp2[0] integerValue];
        NSInteger curInt1 = [verTemp2[1] integerValue];
        NSInteger curInt2 = [verTemp2[2] integerValue];
        
        if (realInt0 <= curInt0) {
            if (realInt1 <= curInt1) {
                if (realInt2 <= curInt2) {
                    NSLog(@"최신버전");
                } else {
                    [self updatePopup];
                }
            } else {
                [self updatePopup];
            }
        } else {
            [self updatePopup];
        }
    } else {
        UIWindow *topWindow = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
        topWindow.rootViewController = [UIViewController new];
        topWindow.windowLevel = UIWindowLevelAlert + 1;
        UIAlertController *warningAlert = [UIAlertController alertControllerWithTitle:@"안내" message:@"오프라인 상태입니다. 네트워크의 연결을 확인해 주세요." preferredStyle:UIAlertControllerStyleAlert];
        
        [warningAlert addAction:[UIAlertAction actionWithTitle:@"확인" style:UIAlertActionStyleCancel handler:^(UIAlertAction *action) {
            topWindow.hidden = YES;
        }]];
        
        [topWindow makeKeyAndVisible];
        [topWindow.rootViewController presentViewController:warningAlert animated:YES completion:nil];
    }

}

- (void) updatePopup
{
    NSLog(@"업데이트 필요!!!");

    UIWindow *topWindow = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
    topWindow.rootViewController = [UIViewController new];
    topWindow.windowLevel = UIWindowLevelAlert + 1;
    UIAlertController *alertController = [UIAlertController alertControllerWithTitle:@"새로운 버전 확인" message:@"Smart확인서 앱이 업데이트 되었습니다. \r\n최신 버전으로 업데이트 해 주세요." preferredStyle:UIAlertControllerStyleAlert];

    [alertController addAction:[UIAlertAction actionWithTitle:@"앱스토어로 이동" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:@"https://itunes.apple.com/us/app/smartga-스마트ga/id1184690357?l=ko&ls=1&mt=8"]];
    }]];
    [topWindow makeKeyAndVisible];
    [topWindow.rootViewController presentViewController:alertController animated:YES completion:nil];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return [super shouldAutorotateToInterfaceOrientation:interfaceOrientation];
}

/* Comment out the block below to over-ride */

/*
- (UIWebView*) newCordovaViewWithFrame:(CGRect)bounds
{
    return[super newCordovaViewWithFrame:bounds];
}
*/

@end

@implementation MainCommandDelegate

/* To override the methods, uncomment the line in the init function(s)
   in MainViewController.m
 */

#pragma mark CDVCommandDelegate implementation

- (id)getCommandInstance:(NSString*)className
{
    return [super getCommandInstance:className];
}

- (NSString*)pathForResource:(NSString*)resourcepath
{
    return [super pathForResource:resourcepath];
}

@end

@implementation MainCommandQueue

/* To override, uncomment the line in the init function(s)
   in MainViewController.m
 */
- (BOOL)execute:(CDVInvokedUrlCommand*)command
{
    return [super execute:command];
}

@end
