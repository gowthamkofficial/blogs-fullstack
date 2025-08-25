// api-endpoints.enum.ts
export enum ApiEndpoints {
    // User Controller
    UPDATE_PROFILE_DETAILS_BY_USERID = 'updateProfileDetails',
    UPDATE_BLOG_BY_BLOGID = 'updateBlog',
    CREATE_BLOG = 'createBlog',
    GET_BLOGS_BY_USERID = 'blogs',
  
    // Session Controller
    REGISTER = 'session/register',
    LOGIN = 'session/login',
    GET_USER_BYUSERID="user",
  
    // File Controller
    PROFILE_UPLOAD_BY_USERID = 'files/profileUpload',
    GET_FILE_BY_ID = 'files',
    DOWNLOAD_FILE_BY_ID = 'files/download',
  
    // Blogs Controller
    ADD_LIKE_BY_USERID_AND_BLOGID = 'addLike',
    ADD_COMMENT = 'addComment',
    GET_BLOG_BY_BLOGID = 'getBlog',
    GET_ALL_LIKES_BY_BLOGID = 'getAllLikes',
    GET_ALL_COMMENTS = 'getAllComments',
    GET_ALL_BLOGS = 'getAllBlogs',
    REMOVE_LIKE_BY_LIKEID = 'removeLike',
    REMOVE_COMMENT_BY_COMMENTID = 'removeComment',
  }
  