package br.com.sign.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun encode(string : String) = BCryptPasswordEncoder().encode(string)

