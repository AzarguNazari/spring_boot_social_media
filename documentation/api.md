

# End points

/api/v1/users                                                       -> POST, GET
/api/v1/users/{user_id}                                             -> GET, put, DELETE
/api/v1/users/{user_id}/friends                                     -> POST, GET
/api/v1/users/{user_id}/friends/{friend_id}                         -> GET, DELETE
/api/v1/users/{user_id}/messages                                    -> POST, GET
/api/v1/users/{user_id}/messages/{message_id}                       -> GET, put, DELETE
/api/v1/users/{user_id}/posts                                       -> POST, GET
/api/v1/users/{user_id}/posts/{post_id}                             -> GET, PUT, DELETE
/api/v1/users/{user_id}/posts/{post_id}/comments                    -> GET, POST
/api/v1/users/{user_id}/posts/{post_id}/comments/{comment_id}       -> GET, PUT, DELETE