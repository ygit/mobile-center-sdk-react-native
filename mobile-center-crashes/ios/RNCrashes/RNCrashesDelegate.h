#import <Foundation/Foundation.h>

@import MobileCenterCrashes;

// Support React Native headers both in the React namespace, where they are in RN version 0.40+,
// and no namespace, for older versions of React Native
#if __has_include(<React/RCTEventEmitter.h>)
#import <React/RCTEventEmitter.h>
#else
#import "RCTEventEmitter.h"
#endif


@interface RNCrashesDelegate : NSObject<MSCrashesDelegate>

@property RCTEventEmitter* eventEmitter;

- (NSArray<NSString *> *)supportedEvents;

@end

