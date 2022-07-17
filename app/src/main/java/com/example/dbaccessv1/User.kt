package com.example.dbaccessv1

class User {
    var id : Int = 0
    var name : String = ""
    var city : String = ""

    constructor(name: String, city: String){
//        this.nameId = nameId nameId: Int,     var nameId : Int = 0
        this.name = name
        this.city= city

    }

    constructor(){
    }

    override fun toString(): String {
        return "$name"
    }
}