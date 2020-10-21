package com.assignment.assignmentapplication.interfacecallbacks

interface SortOptionSelectionInterface {
    /** Type ->>
     * 0 = No selection
     * 1 = Total Sales
     * 2 = Add to cart
     * 3 = Downloads
     * 4 = User sessions
     */
    fun sortByOptionSelection(type:Int)
}