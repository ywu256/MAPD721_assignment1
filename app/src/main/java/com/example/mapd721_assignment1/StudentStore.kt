package com.example.mapd721_assignment1

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class StudentStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("student_data")
        private val STUDENT_NUM_KEY = stringPreferencesKey("student_num")
        private val STUDENT_NAME_KEY = stringPreferencesKey("student_name")
        private val COURSE_NAME_KEY = stringPreferencesKey("course_name")
    }

    // Store student data
    suspend fun saveStudentData(studentNum: String, studentName: String, courseName: String) {
        context.dataStore.edit { preferences ->
            preferences[STUDENT_NUM_KEY] = studentNum
            preferences[STUDENT_NAME_KEY] = studentName
            preferences[COURSE_NAME_KEY] = courseName
        }
    }

    // Load student data
    suspend fun loadStudentData(): Triple<String, String, String> {
        val preferences = context.dataStore.data.first()
        val studentNum = preferences[STUDENT_NUM_KEY] ?: ""
        val studentName = preferences[STUDENT_NAME_KEY] ?: ""
        val courseName = preferences[COURSE_NAME_KEY] ?: ""
        return Triple(studentNum, studentName, courseName)
    }

    // Clear student data
    suspend fun clearStudentData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}