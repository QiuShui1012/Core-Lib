package zh.qiushui.mod.core;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class QSCoreUtil {
    public static final String CORE_ID = "qs-lib";
    public static final String CORE_NAME = "QiuShui1012's Core Lib";
    public static final Logger LOGGER = LoggerFactory.getLogger(CORE_NAME);

    public static final List<String> STRINGS_TRUE = ImmutableList.of(
            "true", "True", "TRUE",
            "ture", "Ture", "TURE",
            "enabled", "Enabled", "ENABLED",
            "enablde", "Enablde", "ENABLDE",
            "enabeld", "Enabeld", "ENABELD",
            "yes", "Yes", "YES", "yse", "Yse", "YSE",
            "1"
    );
    public static final List<String> STRINGS_FALSE = ImmutableList.of(
            "false", "False", "FALSE",
            "flase", "Flase", "FLASE",
            "disabled", "Disabled", "DISABLED",
            "disablde", "Disablde", "DISABLDE",
            "disabeld", "Disabeld", "DISABELD",
            "no", "No", "NO",
            "0"
    );

    public static ResourceLocation idCore(String path) {
        return new ResourceLocation(CORE_ID, path);
    }
    public static ResourceLocation idVanilla(String path) {
        return new ResourceLocation("minecraft", path);
    }

    public static String buildCustomTranslationKey(String modId, String prefix, String suffix) {
        return prefix + modId + suffix;
    }

    public static boolean fixStringToBoolean(String booleanString) {
        if (STRINGS_TRUE.contains(booleanString)) {
            return true;
        } else if (STRINGS_FALSE.contains(booleanString)) {
            return false;
        } else {
            return false;
        }
    }

    public static String fixBooleanToString(boolean booleanValue) {
        return booleanValue ? "true" : "false";
    }

    public static Number fixStringToInteger(String stringValue) {
        long tryLong = Long.parseLong(stringValue);
        if (stringValue.startsWith("-")) {
            if (tryLong > Short.MIN_VALUE) {
                return Short.parseShort(stringValue);
            } else if (tryLong > Integer.MIN_VALUE && tryLong < Short.MIN_VALUE) {
                return Integer.parseInt(stringValue);
            } else {
                return tryLong;
            }
        } else {
            if (tryLong < Short.MAX_VALUE) {
                return Short.parseShort(stringValue);
            } else if (tryLong < Integer.MAX_VALUE && tryLong > Short.MAX_VALUE) {
                return Integer.parseInt(stringValue);
            } else {
                return tryLong;
            }
        }
    }

    public static Number fixStringToDecimal(String stringValue) {
        assert stringValue.contains(".");

        int dotIndex = stringValue.indexOf(".");
        String integerPart = stringValue.substring(0, dotIndex);
        String decimalPart = stringValue.substring(dotIndex);

        return fixStringToInteger(integerPart).longValue() + fixStringToInteger(decimalPart).longValue() / (10L * decimalPart.length());
    }

    public static Byte fixStringToByte(String stringValue) {
        int prefixLength = 0;
        int suffixLength = 0;
        boolean hasPrefix = false;
        boolean hasSuffix = false;

        if (stringValue.startsWith("$") || stringValue.startsWith("%") ||
                stringValue.startsWith("=") || stringValue.startsWith("#")
        ) {
            prefixLength = 1;
            hasPrefix = true;
        } else if (stringValue.endsWith("h")) {
            suffixLength = 1;
            hasSuffix = true;
        } else if (stringValue.startsWith("0x") || stringValue.startsWith("0X") ||
                stringValue.startsWith("\\x") || stringValue.startsWith("&H") ||
                stringValue.startsWith("#x")
        ) {
            prefixLength = 2;
            hasPrefix = true;
        } else if (stringValue.startsWith("&#x")) {
            prefixLength = 3;
            hasPrefix = true;
        } else if (stringValue.startsWith("16#") && stringValue.endsWith("#")) {
            prefixLength = 3;
            hasPrefix = true;
            suffixLength = 1;
            hasSuffix = true;
        } else if (stringValue.startsWith("#16r")) {
            prefixLength = 4;
            hasPrefix = true;
        }

        return (byte) Integer.parseInt(hasPrefix ?
                hasSuffix ?
                        stringValue.substring(prefixLength, stringValue.length() - suffixLength) :
                        stringValue.substring(prefixLength)
                :
                hasSuffix ?
                        stringValue.substring(0, stringValue.length() - suffixLength) :
                        stringValue
                , 16);
    }

    public static <N extends Number> String fixNumberToString(N numberValue) {
        return numberValue instanceof Byte ? String.format("%02X", numberValue) : "" + numberValue;
    }
}
