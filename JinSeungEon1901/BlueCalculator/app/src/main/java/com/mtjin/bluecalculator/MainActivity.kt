package com.mtjin.bluecalculator

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.MotionEvent
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    //수식
    var expression = StringBuilder()

    //타입
    val EMPTY = 0
    val NUMBER = 1
    val ARITHMETIC = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    /*더하기*/
    fun plus(view: View) {
        if (checkTopElementType() == NUMBER) {
            expression.append("+")
            tv_history.text = expression;
        }
    }

    /*뺴기*/
    fun minus(view: View) {
        if (checkTopElementType() == NUMBER) {
            expression.append("―")
            tv_history.text = expression;
        }
    }

    /*곱하기*/
    fun multiply(view: View) {
        if (checkTopElementType() == NUMBER) {
            expression.append("×")
            tv_history.text = expression;
        }
    }

    /*나누기*/
    fun divide(view: View) {
        if (checkTopElementType() == NUMBER) {
            expression.append("÷")
            tv_history.text = expression;
        }
    }

    /*한글자 삭제*/
    fun delete(view: View) {
        if (!expression.isEmpty()) {
            expression.deleteCharAt(expression.length - 1)
            tv_history.text =expression
        }
    }

    /*모두 지우기*/
    fun clear(view: View) {
        expression.clear()
        tv_history.text = ""
        tv_result.text = "0.0"
    }

    /*나머지 연산 % */
    fun remainder(view: View) {
        if (checkTopElementType() == NUMBER) {
            expression.append("%")
            tv_history.text = expression.toString();
        }
    }

    /*계산결과*/
    fun calculate(view: View) {
        //마지막이 숫자인 경우만 계산
        if(checkTopElementType() == NUMBER) {
            val manager = ScriptEngineManager()
            val engine = manager.getEngineByName("javascript")
            //계산할 수 있게 수식 변경
            var replaceResult: String = expression.toString().replace("×", "*")
            replaceResult = replaceResult.toString().replace("÷", "/")
            replaceResult = replaceResult.toString().replace("―", "-")

            val result = engine.eval(replaceResult.toString())
            //다시 UI형식으로
            tv_history.text = result.toString()
            tv_result.text = result.toString()
            expression.clear()
            expression.append(result)
        }else{
            Toast.makeText(this, "수식을 알맞게 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    /*마지막 인덱스 타입*/
    fun checkTopElementType(): Int {
        if (expression.isEmpty()) {
            return EMPTY
        } else if (expression.get(expression.length - 1).toString().contains("0") ||
            expression.get(expression.length - 1).toString().contains("1") ||
            expression.get(expression.length - 1).toString().contains("2") ||
            expression.get(expression.length - 1).toString().contains("3") ||
            expression.get(expression.length - 1).toString().contains("4") ||
            expression.get(expression.length - 1).toString().contains("5") ||
            expression.get(expression.length - 1).toString().contains("6") ||
            expression.get(expression.length - 1).toString().contains("7") ||
            expression.get(expression.length - 1).toString().contains("8") ||
            expression.get(expression.length - 1).toString().contains("9") ||
            expression.get(expression.length - 1).toString().contains(".")
        ) {
            return NUMBER
        } else {
            return ARITHMETIC
        }
    }


    /* 숫자 0 */
    fun number0(view: View) {
        expression.append("0")
        tv_history.text = expression.toString()
    }

    fun number1(view: View) {
        expression.append("1")
        tv_history.text = expression.toString()
    }

    fun number2(view: View) {
        expression.append("2")
        tv_history.text = expression.toString()
    }

    fun number3(view: View) {
        expression.append("3")
        tv_history.text = expression.toString()
    }

    fun number4(view: View) {
        expression.append("4")
        tv_history.text = expression.toString()
    }

    fun number5(view: View) {
        expression.append("5")
        tv_history.text = expression.toString()
    }

    fun number6(view: View) {
        expression.append("6")
        tv_history.text = expression.toString()
    }

    fun number7(view: View) {
        expression.append("7")
        tv_history.text = expression.toString()
    }

    fun number8(view: View) {
        expression.append("8")
        tv_history.text = expression.toString()
    }

    fun number9(view: View) {
        expression.append("9")
        tv_history.text = expression.toString()
    }

    /*점*/
    fun point(view: View) {
        //숫자일 경우만 추가가능 및 연속추가 불가능
        if (checkTopElementType() == NUMBER) {
            if (!expression.get(expression.length - 1).equals(".")) {
                expression.append(".")
                tv_history.text = expression
            }
        }
    }


}

