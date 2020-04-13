package com.parinherm.entity.xtend

import com.parinherm.annotations.Observable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Observable
class Person {
	String firstName
	String lastName
	int amount
	String state
	LocalDate createdDate
	LocalTime createdDateTime
	boolean isMember
	
	
}