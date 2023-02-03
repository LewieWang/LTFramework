package com.lewie.base.common.ext

import androidx.viewbinding.ViewBinding
import com.lewie.base.common.vb.VbItemCell

inline fun <reified CELL : VbItemCell, reified VB : ViewBinding> checkTarget(
    cell: VbItemCell,
    vb: ViewBinding,
    crossinline target: (targetCell: CELL, targetVB: VB) -> Unit
) {
    if (cell is CELL && vb is VB)
        target.invoke(cell, vb)
}
