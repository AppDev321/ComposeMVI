
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.example.core.notification.NotificationType

@Parcelize
data class PushMessage(
    val title: String = "",
    val body: String,
    val notificationId: String = "",
    val notificationType: NotificationType,
    val userId: String = "",
    val txHash: String? = null,
    val date: Long,
    val intercomConversationId: String = ""
) : Parcelable
