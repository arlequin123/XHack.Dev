package com.example.xhackdev.utils

import com.example.xhackdev.domain.models.GroupedCollectionPositionInfo
import com.example.xhackdev.domain.models.RequestsToTeam

fun Collection<RequestsToTeam>.getPositionInformation(indexInOneDimensionalCollection: Int): GroupedCollectionPositionInfo {
    if(this.isNullOrEmpty()) return GroupedCollectionPositionInfo.failed

    val oneGroup = 1

    var groupIndex = 0
    var groupIndexInOneDimensionalCollection = 0

    this.forEach{
        val totalItemsInCurrentGroup = it.size

        if(groupIndexInOneDimensionalCollection == indexInOneDimensionalCollection){
            return GroupedCollectionPositionInfo.createGroupInfo(groupIndex, totalItemsInCurrentGroup)
        }

        val indexInCurrentGroup = indexInOneDimensionalCollection - groupIndexInOneDimensionalCollection - oneGroup

        if(indexInCurrentGroup < totalItemsInCurrentGroup){
            return GroupedCollectionPositionInfo.createItemInGroupInfo(groupIndex, indexInCurrentGroup, totalItemsInCurrentGroup)
        }

        groupIndexInOneDimensionalCollection += totalItemsInCurrentGroup + oneGroup
        groupIndex++
    }

    return GroupedCollectionPositionInfo.failed
}