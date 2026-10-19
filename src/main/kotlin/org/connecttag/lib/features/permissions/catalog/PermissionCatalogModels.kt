package org.connecttag.lib.features.permissions.catalog

public enum class StandardPermissionCategory(val titleAr: String, val titleEn: String) {
    LOCATION("الموقع الجغرافي", "Location"),
    CAMERA("الكاميرا", "Camera"),
    CONTACTS("جهات الاتصال", "Contacts"),
    MICROPHONE("الميكروفون", "Microphone"),
    STORAGE("التخزين والملفات", "Storage"),
    PHONE("الهاتف والمكالمات", "Phone"),
    SMS("الرسائل النصية SMS", "SMS"),
    CALENDAR("التقويم", "Calendar"),
    SENSORS("المستشعرات الصحية", "Body Sensors"),
    ACTIVITY_RECOGNITION("النشاط البدني والحركة", "Physical Activity"),
    BLUETOOTH("البلوتوث والأجهزة القريبة", "Bluetooth & Nearby"),
    NOTIFICATIONS("الإشعارات", "Notifications"),
    ACCOUNTS("الحسابات المسجلة", "Accounts"),
    AUDIO("الصوتيات", "Audio"),
    MEDIA("الصور والفيديوهات", "Media"),
    DOCUMENTS("المستندات", "Documents"),
    WIFI("شبكة Wi-Fi", "Wi-Fi"),
    NETWORK("الإنترنت والشبكة", "Network"),
    BATTERY("البطارية وتحسين الأداء", "Battery"),
    DEVICE("معلومات الجهاز", "Device Information"),
    SYSTEM("إعدادات النظام", "System Settings"),
    INSTALLED_APPS("التطبيقات المثبتة", "Installed Apps"),
    ACCESSIBILITY("إمكانية الوصول", "Accessibility"),
    ADMINISTRATION("إدارة الجهاز", "Device Administration"),
    TIME("الوقت والتاريخ", "Time & Date"),
    ALARM("المنبه والتنبيهات الدقيقة", "Alarms"),
    KEYGUARD("قفل الشاشة", "Keyguard"),
    WALLPAPER("خلفية الشاشة", "Wallpaper"),
    USB("منافذ USB", "USB"),
    NFC("تقنية NFC", "NFC"),
    PRINTING("الطباعة", "Printing"),
    SCREEN("الشاشة والعرض", "Display & Screen"),
    COMPANION_DEVICE("الأجهزة المصاحبة", "Companion Devices"),
    HEALTH("الصحة واللياقة", "Health & Fitness"),
    VOICEMAIL("البريد الصوتي", "Voicemail"),
    CAR("السيارة", "Car & Auto"),
    TV("التلفاز", "TV"),
    WEARABLE("الساعات الذكية", "Wearables"),
    OTHER("صلاحيات عامة أخرى", "Other")
}

public enum class StandardPermissionGrantType {
    USER_CONSENT_REQUIRED,
    AUTO_GRANTED,
    SPECIAL_PERMISSION,
    SYSTEM_ONLY,
    DANGEROUS_REQUIRE_CONSENT
}

public enum class StandardPermissionScope {
    SINGLE_USE,
    BACKGROUND,
    FOREGROUND,
    CONTINUOUS,
    ONE_TIME
}

public enum class StandardPermissionRiskLevel(val titleAr: String, val titleEn: String) {
    SAFE("إذن عادي", "Safe"),
    LOW("إذن قياسي", "Low Risk"),
    MEDIUM("إذن متقدم", "Medium Risk"),
    HIGH("إذن حساس", "High Risk"),
    CRITICAL("إذن حرج", "Critical Risk")
}

public data class PermissionGroupInfo(
    val groupName: String,
    val groupNameAr: String,
    val permissions: List<String>,
    val descriptionAr: String,
    val requiredTogether: Boolean = false
)

public data class StandardPermissionInfo(
    val permissionSuffix: String,
    val titleAr: String,
    val descriptionAr: String,
    val category: StandardPermissionCategory,
    val isDangerous: Boolean = true,
    val grantType: StandardPermissionGrantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
    val scope: StandardPermissionScope = StandardPermissionScope.CONTINUOUS,
    val groupId: String = "",
    val relatedPermissions: List<String> = emptyList(),
    val riskLevel: StandardPermissionRiskLevel = StandardPermissionRiskLevel.MEDIUM,
    val isPrivacySensitive: Boolean = true,
    val androidVersionIntroduced: Int = 1,
    val isDeprecated: Boolean = false
) {
    public companion object {
        public fun normalizePermissionName(permissionName: String): String {
            return permissionName
                .replace("android.permission.", "")
                .replace("android.intent.action.", "")
                .replace("com.android.voicemail.permission.", "")
                .replace("com.google.android.", "")
                .replace("com.google.android.apps.", "")
                .trim()
        }

        public fun unknown(permissionName: String): StandardPermissionInfo {
            val suffix = normalizePermissionName(permissionName).ifBlank { "UNKNOWN" }
            return StandardPermissionInfo(
                permissionSuffix = suffix,
                titleAr = suffix,
                descriptionAr = "لا توجد معلومات تفصيلية لهذه الصلاحية حالياً",
                category = StandardPermissionCategory.OTHER,
                isDangerous = false,
                grantType = StandardPermissionGrantType.AUTO_GRANTED,
                scope = StandardPermissionScope.CONTINUOUS,
                riskLevel = StandardPermissionRiskLevel.SAFE,
                isPrivacySensitive = false
            )
        }
    }
}
