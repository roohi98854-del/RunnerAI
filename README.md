# Runner AI — Trial 0.6.0

نسخه آزمایشی نهایی‌شده برای تست معماری و هسته اپ.

## دو Build
- `userDebug`: نسخه عادی کاربر، بدون Developer VIP
- `developerDebug`: نسخه آزمایشی خصوصی سازنده، با Developer VIP مخفی

Developer VIP در Build مخصوص سازنده فعال است و در نسخه User وجود ندارد.

## VIP سازنده
فقط گزارش/تحلیل:
- AI Coach
- Safety Engine
- System Health
- Running / Rehab / Strength
- Planner / Backlog
- Personal Model

VIP اجازه تغییر خودکار Safety Engine، الگوریتم‌ها، برنامه کاربران یا حذف/اصلاح داده را ندارد.

## وضعیت
هسته محلی، Room، Safety Engine، TLU، Readiness، Risk، Capacity، Personal Model، Planner، Feedback، Backlog و AI Gateway در پروژه وجود دارد.

برای نسخه Production هنوز باید Backend واقعی، AI provider، احراز هویت، GPS/Map، Weather، Notifications، Sync و تست روی دستگاه واقعی متصل شوند.

این محیط Android SDK/Gradle اجرایی ندارد؛ بنابراین APK باینری در اینجا کامپایل نشده و خروجی تحویلی پروژه قابل Build در Android Studio است.
