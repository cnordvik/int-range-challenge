package com.challenge.intervalsmethod

/**
 * Created by Pavel on 25/09/2021.
 **/
class UtilRanges {

    private var validInclusiveIntRanges: MutableSet<IntRange> = mutableSetOf()
    private var uniqueInclusiveIntRanges: MutableSet<IntRange> = mutableSetOf()
    private var uniqueInclusiveToRemove: MutableSet<IntRange> = mutableSetOf()

    fun getValidRanges(inclusiveRangeList: MutableSet<IntRange>, exclusiveRangeList: MutableSet<IntRange>): List<IntRange> {
        if (exclusiveRangeList.isNotEmpty()) {
            inclusiveRangeList.forEach { inclusiveRange ->
                exclusiveRangeList.forEach { exclusiveRange ->
                    if (exclusiveRange.first in inclusiveRange && inclusiveRange.first != exclusiveRange.first) {
                        validInclusiveIntRanges.add(
                            IntRange(inclusiveRange.first, exclusiveRange.first - 1)
                        )
                    }
                    if (exclusiveRange.last in inclusiveRange && exclusiveRange.last != inclusiveRange.last) {
                        validInclusiveIntRanges.add(
                            IntRange(exclusiveRange.last + 1, inclusiveRange.last)
                        )
                    }
                }
            }
        } else {
            validInclusiveIntRanges = inclusiveRangeList
        }
        return groupInclusiveIntRanges(exclusiveRangeList.isEmpty())
    }

    private fun groupInclusiveIntRanges(isExclusiveListEmpty: Boolean): List<IntRange> {
        validInclusiveIntRanges.map { actualRange ->
            validInclusiveIntRanges.forEach {
                when {
                    actualRange == it -> {}// need to skip
                    actualRange.first in it && actualRange.last in it -> {
                        if (isExclusiveListEmpty) uniqueInclusiveIntRanges.add(it) else uniqueInclusiveIntRanges.add(actualRange)
                        return@map
                    }
                    actualRange.first in it && actualRange.last !in it -> {
                        if (isExclusiveListEmpty)
                            uniqueInclusiveIntRanges.add(it.first..actualRange.last)
                        else
                            uniqueInclusiveIntRanges.add(actualRange.first..it.last)
                        return@map
                    }

                    actualRange.first !in it && actualRange.last in it -> {
                        if (isExclusiveListEmpty)
                            uniqueInclusiveIntRanges.add(actualRange.first..it.last)
                        else
                            uniqueInclusiveIntRanges.add(it.first..actualRange.last)
                        return@map
                    }
                    actualRange.first !in it && actualRange.last !in it -> {
                        if(it.first !in actualRange && it.last !in actualRange) {
                            uniqueInclusiveIntRanges.add(actualRange)
                        }
                        return@map
                    }
                }
            }
        }
        if (!isExclusiveListEmpty) removeDuplicateInclusive()
        return uniqueInclusiveIntRanges.sortedBy { it.first }
    }

    private fun removeDuplicateInclusive() {
        uniqueInclusiveIntRanges.map { actualRange ->
            uniqueInclusiveIntRanges.forEach {
                when {
                    actualRange == it -> {} // skip same item
                    actualRange.first == actualRange.last ->
                        uniqueInclusiveToRemove.add(actualRange)
                    actualRange.first == it.first && actualRange.last < it.last ->
                        uniqueInclusiveToRemove.add(it)
                    actualRange.first == it.first && actualRange.last > it.last ->
                        uniqueInclusiveToRemove.add(actualRange)
                    actualRange.last == it.last && actualRange.first < it.first ->
                        uniqueInclusiveToRemove.add(actualRange)
                    actualRange.last == it.last && actualRange.first > it.first ->
                        uniqueInclusiveToRemove.add(it)
                }
            }
        }
        uniqueInclusiveIntRanges.removeAll(uniqueInclusiveToRemove)
    }
}

