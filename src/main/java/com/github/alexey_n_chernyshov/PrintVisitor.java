/**
 * @author Alexey Chernyshov
 */

package com.github.alexey_n_chernyshov;

import java.io.PrintStream;

class PrintVisitor implements GoParserVisitor {

    private PrintStream out = null;

    public PrintVisitor(PrintStream outStream) {
        out = outStream;
    }

    /**
     * Visit all children of node.
     */
    private void visitAllChildren(SimpleNode node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
    }

    @Override
    public Object visit(SimpleNode node, Object data) {
        throw new RuntimeException("Visit SimpleNode");
    }

    @Override
    public Object visit(ASTSourceFile node, Object data) {
        visitAllChildren(node, data);
        return (data);
    }

    @Override
    public Object visit(ASTIntegerLiteral node, Object data) {
        out.print(node.value);
        return data;
    }

    @Override
    public Object visit(ASTFloatLiteral node, Object data) {
        out.print(node.value);
        return data;
    }

    @Override
    public Object visit(ASTImaginaryLiteral node, Object data) {
        out.print(node.value);
        return data;
    }

    @Override
    public Object visit(ASTRuneLiteral node, Object data) {
        out.print(node.value);
        return data;
    }

    @Override
    public Object visit(ASTStringLiteral node, Object data) {
        out.print(node.value);
        return data;
    }

    @Override
    public Object visit(ASTUnaryPlus node, Object data) {
        out.print(node.value);
        node.jjtGetChild(0).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTUnaryMinus node, Object data) {
        out.print(node.value);
        node.jjtGetChild(0).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTUnaryNot node, Object data) {
        out.print(node.value);
        node.jjtGetChild(0).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTUnaryXor node, Object data) {
        out.print(node.value);
        node.jjtGetChild(0).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTUnaryMult node, Object data) {
        out.print(node.value);
        node.jjtGetChild(0).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTUnaryAnd node, Object data) {
        out.print(node.value);
        node.jjtGetChild(0).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTUnaryLessMinus node, Object data) {
        out.print(node.value);
        node.jjtGetChild(0).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTMult node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTDiv node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTDivInt node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTShiftLeft node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTShiftRight node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTAnd node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTAndXor node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTAdd node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTSub node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTOr node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTXor node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTEqual node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTNotEqual node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTLess node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTLessEqual node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTGreater node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTGreaterEqual node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTAndAnd node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTOrOr node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(node.value);
        node.jjtGetChild(1).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        out.print(node.value);
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPackageName node, Object data) {
        out.print(node.value);
        out.print(".");
        return data;
    }

    @Override
    public Object visit(ASTResult node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFunctionBody node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFunctionLit node, Object data) {
        out.print("func(" + node.value + ")");
        return data;
    }

    @Override
    public Object visit(ASTType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTBlock node, Object data) {
        out.println("{");
        visitAllChildren(node, data);
        out.println("}");
        return data;
    }

    @Override
    public Object visit(ASTParameters node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMethodName node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print("(");
        for (int i = 1; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        out.print(") ");
        return data;
    }

    @Override
    public Object visit(ASTInterfaceType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTInterfaceTypeName node, Object data) {
        out.print(node.value);
        return data;
    }

    @Override
    public Object visit(ASTMethodSpec node, Object data) {
        out.print(node.value);
        return data;
    }

    @Override
    public Object visit(ASTPackageClause node, Object data) {
        out.print("package ");
        node.jjtGetChild(0).jjtAccept(this, data);
        out.println(";");
        return data;
    }

    @Override
    public Object visit(ASTImportDecl node, Object data) {
        out.print("import ");
        node.jjtGetChild(0).jjtAccept(this, data);
        out.println(";");
        return data;
    }

    @Override
    public Object visit(ASTImportPath node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTTopLevelDecl node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTFunctionDecl node, Object data) {
        out.print("func ");
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print("(");
        if (node.jjtGetNumChildren() > 1) {
            node.jjtGetChild(1).jjtAccept(this, data);
        }
        out.print(")");
        for (int i = 2; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    @Override
    public Object visit(ASTFunctionName node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStatementList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStatement node, Object data) {
        visitAllChildren(node, data);
        out.println(";");
        return data;
    }

    @Override
    public Object visit(ASTSimpleStatement node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTReceiverType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTMethodExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTOperand node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTArguments node, Object data) {
        out.print("(");
        visitAllChildren(node, data);
        out.print(")");
        return data;
    }

    @Override
    public Object visit(ASTExpressionList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPrimaryExpression node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVarDecl node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTVarSpec node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTShortVarDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTDeclaration node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTAssignment node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(" = ");
        node.jjtGetChild(0).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTMethodDecl node, Object data) {
        out.print("func (");
        node.jjtGetChild(0).jjtAccept(this, data);
        out.print(") ");
        node.jjtGetChild(1).jjtAccept(this, data);
        for (int i = 2; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    @Override
    public Object visit(ASTReciever node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTPointerType node, Object data) {
        out.print("*");
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstDecl node, Object data) {
        out.print("const ");
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTConstSpec node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeDecl node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTTypeSpec node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTArrayType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTStructType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFieldDecl node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTSliceType node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIndex node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTIdentifierList node, Object data) {
        visitAllChildren(node, data);
        return data;
    }

    @Override
    public Object visit(ASTFunctionType node, Object data) {
        out.print("func ");
        visitAllChildren(node, data);
        out.println("");
        return data;
    }

    @Override
    public Object visit(ASTSelector node, Object data) {
        out.print(".");
        visitAllChildren(node, data);
        return data;
    }
}