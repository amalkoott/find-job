package ru.amalkoott.findjob.domain

import kotlinx.coroutines.flow.Flow

abstract class Countable {
    lateinit var count: Flow<Int>
}