//
//  UIColor+SGExctention.m
//  SmartGA
//
//  Created by Hong Junho on 2018. 5. 8..
//

#import "UIColor+SGExtention.h"

@implementation UIColor (SGExtention)

#pragma mark - CBColor

+ (UIColor *) sg_c1Color
{
    return [UIColor colorWith8BitRed:0
                               green:132
                                blue:231];
}

+ (UIColor *) sg_c2Color
{
    return [UIColor colorWith8BitRed:255
                               green:144
                                blue:0];
}

+ (UIColor *) sg_c3Color
{
    return [UIColor colorWith8BitRed:148
                               green:177
                                blue:207];
}

+ (UIColor *) sg_c4Color
{
    return [UIColor colorWith8BitRed:226
                               green:228
                                blue:224];
}

+ (UIColor *) sg_c5Color
{
    return [UIColor colorWith8BitRed:255
                               green:255
                                blue:255];
}

+ (UIColor *) sg_c6Color
{
    return [UIColor colorWith8BitRed:222
                               green:222
                                blue:222];
}

+ (UIColor *) sg_c7Color
{
    return [UIColor colorWith8BitRed:153
                               green:153
                                blue:153];
}

+ (UIColor *) sg_c8Color
{
    return [UIColor colorWith8BitRed:102
                               green:102
                                blue:102];
}

+ (UIColor *) sg_c9Color
{
    return [UIColor colorWith8BitRed:117
                               green:156
                                blue:245];
}

+ (UIColor *) sg_c10Color
{
    return [UIColor colorWith8BitRed:36
                               green:36
                                blue:36];
}

+ (UIColor *) sg_c11Color
{
    return [UIColor colorWith8BitRed:51
                               green:51
                                blue:51];
}

+ (UIColor *) sg_c12Color
{
    return [UIColor colorWith8BitRed:136
                               green:136
                                blue:136];
}

+ (UIColor *) sg_c13Color
{
    return [UIColor colorWith8BitRed:72
                               green:130
                                blue:212];
}

+ (UIColor *) sg_c14Color
{
    return [UIColor colorWith8BitRed:227
                               green:237
                                blue:255];
}

+ (UIColor *) sg_c15Color
{
    return [UIColor colorWith8BitRed:63
                               green:98
                                blue:186];
}

+ (UIColor *) sg_c16Color
{
    return [UIColor colorWith8BitRed:160
                               green:160
                                blue:160];
}

+ (UIColor *) sg_c17Color
{
    //    return [UIColor colorWith8BitRed:70
    //                               green:112
    //                                blue:199
    //                               alpah:0.85];
    return [UIColor colorWith8BitRed:70
                               green:112
                                blue:199
                               alpha:0.85];
}

#pragma mark - Hex colors

+ (UIColor *)colorWithHexString:(NSString *)hexString
{
    return [self colorWithHexString:hexString alpha:1.0f];
}

+ (UIColor *)colorWithHexString:(NSString *)hexString alpha:(CGFloat)alpha
{
    if('#' != [hexString characterAtIndex:0])
        hexString = [NSString stringWithFormat:@"#%@", hexString];
    
    // check for string length
    assert(7 == hexString.length || 4 == hexString.length);
    
    // check for 3 character HexStrings
    hexString = [self hexStringTransformFromThreeCharacters:hexString];
    
    NSString *redHex    = [NSString stringWithFormat:@"0x%@", [hexString substringWithRange:NSMakeRange(1, 2)]];
    unsigned redInt = [self hexValueToUnsigned:redHex];
    
    NSString *greenHex  = [NSString stringWithFormat:@"0x%@", [hexString substringWithRange:NSMakeRange(3, 2)]];
    unsigned greenInt = [self hexValueToUnsigned:greenHex];
    
    NSString *blueHex   = [NSString stringWithFormat:@"0x%@", [hexString substringWithRange:NSMakeRange(5, 2)]];
    unsigned blueInt = [self hexValueToUnsigned:blueHex];
    
    UIColor *color = [UIColor colorWith8BitRed:redInt green:greenInt blue:blueInt alpha:alpha];
    
    return color;
}

+ (UIColor *)colorWith8BitRed:(NSInteger)red green:(NSInteger)green blue:(NSInteger)blue
{
    return [self colorWith8BitRed:red green:green blue:blue alpha:1.0f];
}

+ (UIColor *)colorWith8BitRed:(NSInteger)red green:(NSInteger)green blue:(NSInteger)blue alpha:(CGFloat)alpha
{
    UIColor *color = [UIColor colorWithRed:(float)red/255 green:(float)green/255 blue:(float)blue/255 alpha:alpha];
    return color;
}

+ (NSString *)hexStringTransformFromThreeCharacters:(NSString *)hexString
{
    if(hexString.length == 4)
    {
        hexString = [NSString stringWithFormat:@"#%@%@%@%@%@%@",
                     [hexString substringWithRange:NSMakeRange(1, 1)],[hexString substringWithRange:NSMakeRange(1, 1)],
                     [hexString substringWithRange:NSMakeRange(2, 1)],[hexString substringWithRange:NSMakeRange(2, 1)],
                     [hexString substringWithRange:NSMakeRange(3, 1)],[hexString substringWithRange:NSMakeRange(3, 1)]];
    }
    
    return hexString;
}

+ (unsigned)hexValueToUnsigned:(NSString *)hexValue
{
    unsigned value = 0;
    
    NSScanner *hexValueScanner = [NSScanner scannerWithString:hexValue];
    [hexValueScanner scanHexInt:&value];
    
    return value;
}

- (UIImage *)imageValue
{
    CGRect rect = CGRectMake(0.0f, 0.0f, 1.0f, 1.0f);
    UIGraphicsBeginImageContext(rect.size);
    CGContextRef context = UIGraphicsGetCurrentContext();
    
    CGContextSetFillColorWithColor(context, [self CGColor]);
    CGContextFillRect(context, rect);
    
    UIImage *image = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    return image;
}

@end
