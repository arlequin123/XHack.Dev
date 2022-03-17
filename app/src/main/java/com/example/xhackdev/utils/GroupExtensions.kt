package com.example.xhackdev.utils

import com.example.xhackdev.domain.interfaces.IGroup
import com.example.xhackdev.domain.models.GroupedCollectionPositionInfo


fun Collection<IGroup>.countGroupsAndItems(): Int{
    if(this.isNullOrEmpty()) return 0

    var count = 0

    this.forEach {
        count++
        count += it.size
    }

    return count
}


fun Collection<IGroup>.getPositionInformation(indexInOneDimensionalCollection: Int): GroupedCollectionPositionInfo {
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



