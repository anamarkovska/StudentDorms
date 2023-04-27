package com.example.studentdorms.domain.dto

class PostDto {
    var id: Long? = null
    var title: String = ""
    var content: String = ""
    var userDto: UserDto? = null
    var likedBy: MutableSet<UserDto> = mutableSetOf()
    var postCategoryDto: PostCategoryDto? = null
    var comments: List<CommentDto> = emptyList() // Added comments field

    constructor()

    constructor(
        id: Long?,
        title: String,
        content: String,
        userDto: UserDto?,
        likedBy: MutableSet<UserDto>,
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
        userDto: UserDto?,
        likedBy: List<UserDto>,
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
