package com.elpts

import sberid.sdk.auth.PkceUtils
import sberid.sdk.auth.SberIDLoginManager
import java.security.SecureRandom

class SberUri {

    //Создание параметров для поддержки протокола PKCE.
    val codeVerifier = PkceUtils.generateRandomCodeVerifier(SecureRandom())
    val codeChallenge = PkceUtils.deriveCodeVerifierChallenge(codeVerifier)


    //Создание Uri с параметрами для аутентификации, все значения нужно поменять на свои, тут указаны примеры
    val uri = SberIDLoginManager.SberIDBuilder()
            .clientID("512e1152-e14c-5223-a125-d519eb68bb86")
            .scope("openid name email mobile birthdate gender")
            .state("ffad1d59c1e34844a1415226103d44f3")
            .nonce("b1947d4f10a24eb0a21155239be9b066")
            .redirectUri("app://elpts_app/")
            .codeChallenge(codeChallenge) //Необязательный параметр
            .codeChallengeMethod(PkceUtils.getCodeChallengeMethod()) //Необязательный параметр
            .build()

}
