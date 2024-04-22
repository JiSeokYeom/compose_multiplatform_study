import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

interface Platform {
    val name: String
}

fun appModule() = listOf(platformModule)


val platformModule = module {
    singleOf(::getPlatform)
}
val appModule = module {
    single { testt() }
}

class testt(){
    fun provideMessage(): String = "안녕 모듈"
}

/*expect class jenstPlatform {
    val name: String
}*/

expect fun getPlatform(): Platform