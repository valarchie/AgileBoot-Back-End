package com.agileboot.domain.system;

import cn.hutool.core.lang.tree.Tree;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TreeSelectedDTO {

    private List<Long> checkedKeys;
    private List<Tree<Long>> menus;
    private List<Tree<Long>> depts;

}
