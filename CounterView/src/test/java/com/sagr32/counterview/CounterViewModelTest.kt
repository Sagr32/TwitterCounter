package com.sagr32.counterview


//
//@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class)
//class TweetViewModelTest {
//
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var viewModel: TweetViewModel
//    @Mock
//    private lateinit var calculateTweetLengthUseCase: CalculateTweetLengthUseCase
//
//    @Mock
//    private lateinit var checkTweetValidityUseCase: CheckTweetValidityUseCase
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.openMocks(this)
//        viewModel = TweetViewModel(calculateTweetLengthUseCase, checkTweetValidityUseCase)
//    }
//
//    @Test
//    fun testUpdateTweetText() {
//        viewModel.updateTweetText("Hello")
//        val state = viewModel.state.value
//        assertEquals(5, state.typedCharacters)
//        assertEquals(TweetViewModel.TWITTER_MAX_CHAR - 5, state.remainingCharacters)
//        assertTrue(state.isValid)
//    }
//
//    @Test
//    fun testTweetExceedsCharacterLimit() {
//        val longText = "A".repeat(TweetViewModel.TWITTER_MAX_CHAR + 1)
//        viewModel.updateTweetText(longText)
//        val state = viewModel.state.value
//        assertFalse(state.isValid)
//    }
//}
