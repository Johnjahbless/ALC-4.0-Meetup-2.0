package com.app.android.june.alcmeetup20

class user {
    var name: String? = null
    var phone: String? = null
    var email: String? = null

    constructor() {}

    constructor(name: String, phone: String, email: String) {
        this.name = name
        this.phone = phone
        this.email = email
    }


}
