package com.github.alexey_n_chernyshov;

/**
 * @author Yex
 */
public abstract class AbstractASTVisitor implements GoParserVisitor {

    protected void visitAllChildren(SimpleNode node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
    }

}
