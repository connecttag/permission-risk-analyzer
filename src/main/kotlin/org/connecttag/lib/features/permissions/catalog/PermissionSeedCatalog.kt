package org.connecttag.lib.features.permissions.catalog

/**
 * Canonical Single Source of Truth for Android Permissions Catalog across ConnectTag Suite.
 * Contains detailed metadata, categorization, security risk grading, and descriptions.
 */
public enum class PermissionSeedCatalog(
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
    // ==================== LOCATION PERMISSIONS ====================
    ACCESS_FINE_LOCATION(
        permissionSuffix = "ACCESS_FINE_LOCATION",
        titleAr = "الموقع الدقيق",
        descriptionAr = "الوصول إلى موقعك الجغرافي بدقة عالية باستخدام GPS وشبكات Wi-Fi والبلوتوث",
        category = StandardPermissionCategory.LOCATION,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "LOCATION_GROUP",
        relatedPermissions = listOf("ACCESS_COARSE_LOCATION", "ACCESS_BACKGROUND_LOCATION"),
        riskLevel = StandardPermissionRiskLevel.HIGH
    ),
    ACCESS_COARSE_LOCATION(
        permissionSuffix = "ACCESS_COARSE_LOCATION",
        titleAr = "الموقع التقريبي",
        descriptionAr = "الوصول إلى موقعك الجغرافي بشكل تقريبي باستخدام شبكات المحمول وWi-Fi",
        category = StandardPermissionCategory.LOCATION,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "LOCATION_GROUP",
        relatedPermissions = listOf("ACCESS_FINE_LOCATION", "ACCESS_BACKGROUND_LOCATION"),
        riskLevel = StandardPermissionRiskLevel.MEDIUM
    ),
    ACCESS_BACKGROUND_LOCATION(
        permissionSuffix = "ACCESS_BACKGROUND_LOCATION",
        titleAr = "الموقع في الخلفية",
        descriptionAr = "الوصول إلى موقعك أثناء تشغيل التطبيق في الخلفية حتى عندما لا تستخدمه",
        category = StandardPermissionCategory.LOCATION,
        grantType = StandardPermissionGrantType.SPECIAL_PERMISSION,
        scope = StandardPermissionScope.BACKGROUND,
        groupId = "LOCATION_GROUP",
        relatedPermissions = listOf("ACCESS_FINE_LOCATION", "ACCESS_COARSE_LOCATION"),
        riskLevel = StandardPermissionRiskLevel.HIGH,
        androidVersionIntroduced = 29
    ),
    ACCESS_MEDIA_LOCATION(
        permissionSuffix = "ACCESS_MEDIA_LOCATION",
        titleAr = "موقع الوسائط",
        descriptionAr = "الوصول إلى معلومات الموقع المضمنة في الصور ومقاطع الفيديو",
        category = StandardPermissionCategory.LOCATION,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.ONE_TIME,
        groupId = "LOCATION_GROUP",
        relatedPermissions = listOf("ACCESS_FINE_LOCATION"),
        riskLevel = StandardPermissionRiskLevel.MEDIUM,
        androidVersionIntroduced = 29
    ),

    // ==================== STORAGE & MEDIA PERMISSIONS ====================
    READ_EXTERNAL_STORAGE(
        permissionSuffix = "READ_EXTERNAL_STORAGE",
        titleAr = "قراءة التخزين الخارجي",
        descriptionAr = "الوصول لقراءة الملفات والصور والفيديوهات على التخزين الخارجي",
        category = StandardPermissionCategory.STORAGE,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "STORAGE_GROUP",
        relatedPermissions = listOf("WRITE_EXTERNAL_STORAGE", "MANAGE_EXTERNAL_STORAGE"),
        riskLevel = StandardPermissionRiskLevel.MEDIUM
    ),
    WRITE_EXTERNAL_STORAGE(
        permissionSuffix = "WRITE_EXTERNAL_STORAGE",
        titleAr = "كتابة التخزين الخارجي",
        descriptionAr = "تعديل وإنشاء وحذف الملفات على التخزين الخارجي",
        category = StandardPermissionCategory.STORAGE,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "STORAGE_GROUP",
        relatedPermissions = listOf("READ_EXTERNAL_STORAGE", "MANAGE_EXTERNAL_STORAGE"),
        riskLevel = StandardPermissionRiskLevel.MEDIUM
    ),
    MANAGE_EXTERNAL_STORAGE(
        permissionSuffix = "MANAGE_EXTERNAL_STORAGE",
        titleAr = "إدارة التخزين الشاملة",
        descriptionAr = "إدارة كاملة لجميع الملفات على التخزين الخارجي بدون قيود",
        category = StandardPermissionCategory.STORAGE,
        grantType = StandardPermissionGrantType.SPECIAL_PERMISSION,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "STORAGE_GROUP",
        relatedPermissions = listOf("READ_EXTERNAL_STORAGE", "WRITE_EXTERNAL_STORAGE"),
        riskLevel = StandardPermissionRiskLevel.HIGH,
        androidVersionIntroduced = 30
    ),
    READ_MEDIA_IMAGES(
        permissionSuffix = "READ_MEDIA_IMAGES",
        titleAr = "قراءة الصور",
        descriptionAr = "الوصول لقراءة الصور المخزنة على الجهاز",
        category = StandardPermissionCategory.MEDIA,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "MEDIA_GROUP",
        riskLevel = StandardPermissionRiskLevel.MEDIUM,
        androidVersionIntroduced = 33
    ),
    READ_MEDIA_VIDEO(
        permissionSuffix = "READ_MEDIA_VIDEO",
        titleAr = "قراءة الفيديوهات",
        descriptionAr = "الوصول لقراءة مقاطع الفيديو المخزنة على الجهاز",
        category = StandardPermissionCategory.MEDIA,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "MEDIA_GROUP",
        riskLevel = StandardPermissionRiskLevel.MEDIUM,
        androidVersionIntroduced = 33
    ),
    READ_MEDIA_AUDIO(
        permissionSuffix = "READ_MEDIA_AUDIO",
        titleAr = "قراءة الصوتيات",
        descriptionAr = "الوصول لقراءة الملفات الصوتية والموسيقى",
        category = StandardPermissionCategory.MEDIA,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "MEDIA_GROUP",
        riskLevel = StandardPermissionRiskLevel.MEDIUM,
        androidVersionIntroduced = 33
    ),

    // ==================== CAMERA & AUDIO ====================
    CAMERA(
        permissionSuffix = "CAMERA",
        titleAr = "الكاميرا",
        descriptionAr = "التقاط الصور وتسجيل مقاطع الفيديو عبر الكاميرات",
        category = StandardPermissionCategory.CAMERA,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.FOREGROUND,
        groupId = "CAMERA_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH
    ),
    RECORD_AUDIO(
        permissionSuffix = "RECORD_AUDIO",
        titleAr = "الميكروفون وتسجيل الصوت",
        descriptionAr = "تسجيل الصوت باستخدام الميكروفون المدمج",
        category = StandardPermissionCategory.MICROPHONE,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.FOREGROUND,
        groupId = "MICROPHONE_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH
    ),

    // ==================== CONTACTS & ACCOUNTS ====================
    READ_CONTACTS(
        permissionSuffix = "READ_CONTACTS",
        titleAr = "قراءة جهات الاتصال",
        descriptionAr = "الوصول إلى قائمة الأسماء وأرقام الهواتف المحفوظة",
        category = StandardPermissionCategory.CONTACTS,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "CONTACTS_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH
    ),
    WRITE_CONTACTS(
        permissionSuffix = "WRITE_CONTACTS",
        titleAr = "تعديل جهات الاتصال",
        descriptionAr = "إضافة أو تعديل أو حذف الأسماء في دفتر العناوين",
        category = StandardPermissionCategory.CONTACTS,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "CONTACTS_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH
    ),
    GET_ACCOUNTS(
        permissionSuffix = "GET_ACCOUNTS",
        titleAr = "استعراض الحسابات",
        descriptionAr = "معرفة الحسابات المسجلة على الهاتف (جوجل، وغيرها)",
        category = StandardPermissionCategory.ACCOUNTS,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "CONTACTS_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH
    ),

    // ==================== PHONE & SMS ====================
    READ_PHONE_STATE(
        permissionSuffix = "READ_PHONE_STATE",
        titleAr = "حالة الهاتف والمكالمات",
        descriptionAr = "معرفة حالة الشبكة ومعرف الجهاز ومكالمات الهاتف الحالية",
        category = StandardPermissionCategory.PHONE,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "PHONE_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH
    ),
    CALL_PHONE(
        permissionSuffix = "CALL_PHONE",
        titleAr = "إجراء المكالمات مباشرة",
        descriptionAr = "إجراء اتصال هاتفي تلقائياً دون المرور بواجهة الاتصال",
        category = StandardPermissionCategory.PHONE,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.FOREGROUND,
        groupId = "PHONE_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH
    ),
    READ_CALL_LOG(
        permissionSuffix = "READ_CALL_LOG",
        titleAr = "قراءة سجل المكالمات",
        descriptionAr = "الاطلاع على أرقام وتفاصيل المكالمات الصادرة والواردة",
        category = StandardPermissionCategory.PHONE,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "PHONE_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH
    ),
    SEND_SMS(
        permissionSuffix = "SEND_SMS",
        titleAr = "إرسال الرسائل النصية (SMS)",
        descriptionAr = "إرسال رسائل قصيرة قد يترتب عليها رسوم مالية",
        category = StandardPermissionCategory.SMS,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.FOREGROUND,
        groupId = "SMS_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH
    ),
    READ_SMS(
        permissionSuffix = "READ_SMS",
        titleAr = "قراءة الرسائل النصية (SMS)",
        descriptionAr = "قراءة الرسائل القصيرة بما فيها رسائل التحقق OTP",
        category = StandardPermissionCategory.SMS,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "SMS_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH
    ),
    RECEIVE_SMS(
        permissionSuffix = "RECEIVE_SMS",
        titleAr = "استقبال الرسائل النصية",
        descriptionAr = "مراقبة واستقبال الرسائل النصية الواردة بالخلفية",
        category = StandardPermissionCategory.SMS,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.BACKGROUND,
        groupId = "SMS_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH
    ),

    // ==================== SYSTEM & SPECIAL ====================
    SYSTEM_ALERT_WINDOW(
        permissionSuffix = "SYSTEM_ALERT_WINDOW",
        titleAr = "الظهور فوق التطبيقات الأخرى",
        descriptionAr = "عرض نوافذ عائمة ومحتوى فوق شاشات التطبيقات الأخرى",
        category = StandardPermissionCategory.SYSTEM,
        grantType = StandardPermissionGrantType.SPECIAL_PERMISSION,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "SYSTEM_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH,
        androidVersionIntroduced = 23
    ),
    REQUEST_INSTALL_PACKAGES(
        permissionSuffix = "REQUEST_INSTALL_PACKAGES",
        titleAr = "تثبيت التطبيقات من مصادر غير معروفة",
        descriptionAr = "تثبيت ملفات APK خارجية على الهاتف",
        category = StandardPermissionCategory.SYSTEM,
        grantType = StandardPermissionGrantType.SPECIAL_PERMISSION,
        scope = StandardPermissionScope.ONE_TIME,
        groupId = "SYSTEM_GROUP",
        riskLevel = StandardPermissionRiskLevel.HIGH,
        androidVersionIntroduced = 26
    ),
    POST_NOTIFICATIONS(
        permissionSuffix = "POST_NOTIFICATIONS",
        titleAr = "عرض الإشعارات",
        descriptionAr = "إرسال التنبيهات والإشعارات في شريط الحالة",
        category = StandardPermissionCategory.NOTIFICATIONS,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.FOREGROUND,
        groupId = "NOTIFICATION_GROUP",
        riskLevel = StandardPermissionRiskLevel.LOW,
        androidVersionIntroduced = 33
    ),
    QUERY_ALL_PACKAGES(
        permissionSuffix = "QUERY_ALL_PACKAGES",
        titleAr = "الاستعلام عن جميع التطبيقات المثبتة",
        descriptionAr = "معرفة كافة التطبيقات والبرامج المثبتة على الجهاز",
        category = StandardPermissionCategory.INSTALLED_APPS,
        grantType = StandardPermissionGrantType.USER_CONSENT_REQUIRED,
        scope = StandardPermissionScope.CONTINUOUS,
        groupId = "INSTALLED_APPS_GROUP",
        riskLevel = StandardPermissionRiskLevel.MEDIUM,
        androidVersionIntroduced = 30
    );

    public fun toStandardPermissionInfo(): StandardPermissionInfo {
        return StandardPermissionInfo(
            permissionSuffix = permissionSuffix,
            titleAr = titleAr,
            descriptionAr = descriptionAr,
            category = category,
            isDangerous = isDangerous,
            grantType = grantType,
            scope = scope,
            groupId = groupId,
            relatedPermissions = relatedPermissions,
            riskLevel = riskLevel,
            isPrivacySensitive = isPrivacySensitive,
            androidVersionIntroduced = androidVersionIntroduced,
            isDeprecated = isDeprecated
        )
    }

    public companion object {
        public fun findByPermissionName(name: String): PermissionSeedCatalog? {
            val normalized = StandardPermissionInfo.normalizePermissionName(name)
            return entries.firstOrNull {
                it.permissionSuffix.equals(normalized, ignoreCase = true) ||
                    normalized.endsWith(it.permissionSuffix, ignoreCase = true)
            }
        }
    }
}
