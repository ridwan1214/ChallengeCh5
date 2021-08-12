package com.ridwan1214.challengech5

class Controller(private val callBack: CallBack) {
    fun logicGame(pilihanPlayer: String?, pilihanPlayer2: String?) {

        //player 1 win
        if (((pilihanPlayer == "batu") && (pilihanPlayer2 == "gunting")) ||
            ((pilihanPlayer == "kertas") && (pilihanPlayer2 == "batu")) ||
            ((pilihanPlayer == "gunting") && (pilihanPlayer2 == "kertas"))
        ) callBack.tampilkanHasil("1")

        //computer win
        else if (pilihanPlayer2 == "batu" && (pilihanPlayer == "gunting") ||
            ((pilihanPlayer2 == "kertas") && (pilihanPlayer == "batu")) ||
            ((pilihanPlayer2 == "gunting") && (pilihanPlayer == "kertas"))
        ) callBack.tampilkanHasil("2")

        //draw
        else
            callBack.tampilkanHasil("3")
    }
}