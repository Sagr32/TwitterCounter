package com.sagr32.counterview.presentation


import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleObserver
import com.sagr32.counterview.data.models.TweetState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.get
import com.sagr32.counterview.R

@AndroidEntryPoint
class TweetCounter @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), LifecycleObserver {

    private val editText: AppCompatEditText
    private val remainingTextView: AppCompatTextView
    private val typedTextView: AppCompatTextView

    private val viewModel by lazy {
        ViewModelProvider(findViewTreeViewModelStoreOwner()!!).get<TweetViewModel>()
    }
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        observeViewModel()
    }


    init {
        inflate(context, R.layout.view_tweet_counter, this)
        editText = findViewById(R.id.etTypingBox)
        remainingTextView = findViewById(R.id.tvCharacterRemainingValue)
        typedTextView = findViewById(R.id.tvCharacterTypedValue)

        editText.addTextChangedListener { text ->
            viewModel.updateTweetText(text.toString())
        }

    }

    private fun observeViewModel() {
        coroutineScope.launch {
            viewModel.state.collect { state ->
                updateCharacterCount(state)
            }
        }
    }

    private fun updateCharacterCount(state: TweetState) {
        val remainingChars = state.remainingCharacters
        val typedChars = state.typedCharacters
        typedTextView.text = context.getString(R.string.typed_characters, typedChars.toString())
        remainingTextView.text = remainingChars.toString()

    }

    fun clearText() {
        editText.text?.clear()
    }

    fun getTweetText(): String {
        return editText.text.toString()
    }


}
