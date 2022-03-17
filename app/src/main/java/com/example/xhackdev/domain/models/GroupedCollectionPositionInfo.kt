package com.example.xhackdev.domain.models

class GroupedCollectionPositionInfo private constructor(
    val groupIndex: Int,
    val isGroup: Boolean,
    val itemIndexInGroup: Int,
    val itemsCountInGroup: Int) {

    companion object {
        val failed = GroupedCollectionPositionInfo(-1, false, -1, -1)

        fun createGroupInfo(groupIndex: Int, itemCountInGroup: Int) =
            GroupedCollectionPositionInfo(groupIndex, true, -1, itemCountInGroup)

        fun createItemInGroupInfo(groupIndex: Int, itemIndexInGroup: Int, itemCountInGroup: Int) =
            GroupedCollectionPositionInfo(groupIndex, false, itemIndexInGroup, itemCountInGroup)
    }
}
