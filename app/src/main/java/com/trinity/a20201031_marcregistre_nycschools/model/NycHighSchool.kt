package com.trinity.a20201031_marcregistre_nycschools.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// using kotlin to reduce the amount of code that would have to be written if java was used.
// since i'm passing this object through a parceable, then I added the Parelize annotation.
@SuppressLint("ParcelCreator")
@Parcelize
data class NycHighSchool(
    val dbn: String? = null,
    val school_name: String? = null,
    val boro: String? = null,
    val overview_paragraph: String? = null,
    val school_10th_seats: String? = null,
    val academicopportunities1: String? = null,
    val academicopportunities2: String? = null,
    val academicopportunities3: String? = null,
    val ell_programs: String? = null,
    val language_classes: String? = null,
    val neighborhood: String? = null,
    val building_code: String? = null,
    val location: String? = null,
    val phone_number: String? = null,
    val fax_number: String? = null,
    val school_email: String? = null,
    val website: String? = null,
    val subway: String? = null,
    val bus: String? = null,
    val grades2018: String? = null,
    val finalgrades: String? = null,
    val total_students: String? = null,
    val addtl_info1: String? = null,
    val extracurricular_activities: String? = null,
    val school_sports: String? = null,
    val attendance_rate: String? = null,
    val pct_stu_enough_variety: String? = null,
    val pct_stu_safe: String? = null,
    val transfer: String? = null,
    val directions1: String? = null,
    val program1: String? = null,
    val code1: String? = null,
    val interest1: String? = null,
    val method1: String? = null,
    val seats9ge1: String? = null,
    val grade9gefilledflag1: String? = null,
    val grade9geapplicants1: String? = null,
    val seats9swd1: String? = null,
    val grade9swdfilledflag1: String? = null,
    val grade9swdapplicants1: String? = null,
    val seats101: String? = null,
    val eligibility1: String? = null,
    val grade9geapplicantsperseat1: String? = null,
    val grade9swdapplicantsperseat1: String? = null,
    val primary_address_line_1: String? = null,
    val city: String? = null,
    val zip: String? = null,
    val state_code: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val community_board: String? = null,
    val council_district: String? = null,
    val census_tract: String? = null,
    val bin: String? = null,
    val bbl: String? = null,
    val nta: String? = null,
    val borough: String? = null
): Parcelable