package com.example.studentdorms.domain.dto

class PostDto {
    var id: Long? = null
    private var title: String = ""
    private var content: String = ""
    private var userDto: UserAppDto? = null
    private var likedBy: MutableSet<UserAppDto> = mutableSetOf()
    private var postCategoryDto: PostCategoryDto? = null
    private var comments: List<CommentDto> = emptyList() // Added comments field

    constructor()

    constructor(
        id: Long?,
        title: String,
        content: String,
        userDto: UserAppDto?,
        likedBy: MutableSet<UserAppDto>,
        postCategoryDto: PostCategoryDto?,
        comments: List<CommentDto> // Added comments parameter
    ) {
        this.id = id
        this.title = title
        this.content = content
        this.userDto = userDto
        this.likedBy = likedBy
        this.postCategoryDto = postCategoryDto
        this.comments = comments // Set comments field
    }

    constructor(
        id: Long?,
        title: String,
        content: String,
        userDto: UserAppDto?,
        likedBy: List<UserAppDto>,
        postCategoryDto: PostCategoryDto?,
        comments: List<CommentDto> // Added comments parameter
    ){
        this.id = id
        this.title = title
        this.content = content
        this.userDto = userDto
        this.likedBy.addAll(likedBy)
        this.postCategoryDto = postCategoryDto
        this.comments = comments // Set comments field
    }
}
