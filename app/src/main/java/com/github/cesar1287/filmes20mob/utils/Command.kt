package com.github.cesar1287.filmes20mob.utils

sealed class Command {
    class Loading(val value: Boolean): Command()
    class Error(val error: String?): Command()
}