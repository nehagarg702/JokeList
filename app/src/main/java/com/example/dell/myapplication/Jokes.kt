/*
to take joke data
 */
package com.example.dell.myapplication

class Jokes {

    var mId: Long = 0
    var joke: String=""

    constructor(id: Long, name: String) {
        mId = id
        joke = name
    }

    constructor(Jokes: Jokes) {
        mId = Jokes.mId
        joke = Jokes.joke
    }

}
