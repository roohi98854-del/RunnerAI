package com.example.runnerai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { RunnerAIApp() }
    }
}

@Composable
fun RunnerAIApp() {
    var selected by remember { mutableStateOf(0) }
    var showVip by remember { mutableStateOf(false) }
    var taps by remember { mutableStateOf(0) }

    val tabs = listOf("🏠 Home", "🏃 Running", "🍎 Nutrition", "📊 Progress", "⚙️ Profile")

    if (showVip && BuildConfig.DEVELOPER_VIP) {
        DeveloperVipScreen(onClose = { showVip = false })
        return
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { i, label ->
                    NavigationBarItem(
                        selected = selected == i,
                        onClick = { selected = i },
                        icon = { Text(label.substringBefore(" ")) },
                        label = { Text(label.substringAfter(" ")) }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(30.dp))
            Text(
                "Runner AI",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(12.dp)
                    .then(
                        Modifier
                    )
            )
            Text("نسخه آزمایشی 0.6.0")

            Spacer(Modifier.height(30.dp))
            when (selected) {
                0 -> HomePreview()
                1 -> RunningPreview()
                2 -> NutritionPreview()
                3 -> ProgressPreview()
                4 -> ProfilePreview()
            }

            if (BuildConfig.DEVELOPER_VIP) {
                Spacer(Modifier.height(24.dp))
                // Hidden developer entry: only exists in the developer build.
                TextButton(onClick = {
                    taps++
                    if (taps >= 7) {
                        taps = 0
                        showVip = true
                    }
                }) { Text(" ") }
            }
        }
    }
}

@Composable fun HomePreview() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(18.dp)) {
            Text("امروز", style = MaterialTheme.typography.titleLarge)
            Text("🟢 وضعیت: آماده")
            Text("Readiness  •  Load  •  Risk  •  Capacity")
            Spacer(Modifier.height(8.dp))
            Text("تمرین اصلی امروز")
            Text("برنامه بر اساس وضعیت بدن و Safety Engine تعیین می‌شود.")
        }
    }
}

@Composable fun RunningPreview() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(18.dp)) {
            Text("Running", style = MaterialTheme.typography.titleLarge)
            Text("آرامش • هیجان • مسیر رکورد")
            Text("اجرای واقعی GPS در اتصال تولیدی فعال خواهد شد.")
        }
    }
}

@Composable fun NutritionPreview() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(18.dp)) {
            Text("Nutrition", style = MaterialTheme.typography.titleLarge)
            Text("ثبت غذا، عکس، بارکد، آب، روزه، کالری و درشت‌مغذی‌ها")
        }
    }
}

@Composable fun ProgressPreview() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(18.dp)) {
            Text("Progress & Reports", style = MaterialTheme.typography.titleLarge)
            Text("روند تمرین، درد، ریکاوری، TLU و عملکرد")
        }
    }
}

@Composable fun ProfilePreview() {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(18.dp)) {
            Text("Profile / Settings", style = MaterialTheme.typography.titleLarge)
            Text("پروفایل، محدودیت‌ها، مجوز AI، حریم خصوصی و تنظیمات")
        }
    }
}

@Composable
fun DeveloperVipScreen(onClose: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Developer VIP") })
        }
    ) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("پنل خصوصی سازنده", style = MaterialTheme.typography.headlineSmall)
            Text("فقط برای تحلیل و مدیریت سیستم؛ کاربران عادی این پنل را ندارند.")
            VipCard("AI Coach", "تحلیل عملکرد، خطاها و کیفیت پاسخ‌ها")
            VipCard("Safety Engine", "پایش تصمیم‌های ایمنی و موارد غیرعادی")
            VipCard("System Health", "وضعیت دیتابیس، ذخیره‌سازی و خطاهای برنامه")
            VipCard("Running / Rehab / Strength", "آمار عملکرد و نقاط مشکل‌دار")
            VipCard("Planner / Backlog", "پایش برنامه‌ریزی و بازیابی عقب‌افتادگی")
            VipCard("Personal Model", "کیفیت پیش‌بینی و خطای مدل شخصی")
            Text("⚠️ VIP فقط گزارش می‌دهد؛ تغییر خودکار الگوریتم، Safety یا داده مجاز نیست.")
            Button(onClick = onClose) { Text("بازگشت") }
        }
    }
}

@Composable
fun VipCard(title: String, body: String) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(14.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(body)
        }
    }
}
