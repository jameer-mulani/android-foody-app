package com.example.foodyappstefan2023.data.repository

import com.example.foodyappstefan2023.data.local.LocalDataSource
import com.example.foodyappstefan2023.data.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class FoodRepository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) {

    val remoteDataSource = remoteDataSource;
    val localDataSource = localDataSource;

}