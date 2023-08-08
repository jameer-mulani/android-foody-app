package com.example.foodyappstefan2023.models.repository

import com.example.foodyappstefan2023.models.network.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class FoodRepository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {

    val remoteDataSource = remoteDataSource;
}