package dev.iaiabot.furuhuru

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}