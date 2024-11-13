package com.example.core.database.repo.dictionary

import com.example.core.database.dao.DictionaryDao
import com.example.core.database.entity.DictionaryEntity
import javax.inject.Inject

class DictionaryRepoImpl @Inject constructor(
    private val dictionaryDao: DictionaryDao
) : DictionaryRepo {

    override suspend fun getDictionaryDataList(): List<DictionaryEntity> {
        return dictionaryDao.getDictionaryData()
    }

    override suspend fun getDictionaryRandomOptions(): List<String> {
        val list = dictionaryDao.getRandomOption()
        val listStrings = arrayListOf<String>()
        list.map {
            listStrings.add(it.word.orEmpty())
        }
        return listStrings
    }

    override suspend fun getDictionaryRandomWord(): List<String> {
        return arrayListOf(
            dictionaryDao.getRandomWord().meaning.orEmpty(),
            dictionaryDao.getRandomWord().word.orEmpty()
        )
    }

    override suspend fun getDictionaryRandomDictionaryObject(): DictionaryEntity {
        return dictionaryDao.getRandomWord()
    }
}