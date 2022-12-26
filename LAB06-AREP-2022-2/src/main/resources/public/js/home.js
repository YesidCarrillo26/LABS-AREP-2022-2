var homeApi = (function() {

    let _postId = 0;

    const _addConvertToThreadOption = (publishedPost) => {
        if (_postId > 1) {
            document.getElementById("converToThreadButton").remove();
        }
        let newOption =
            '<div class="create-thread-container">' +
                '<button id="converToThreadButton" onclick="homeApi.createPostFromThread()" >Create thread</button>' +
            '</div>' +
            '<hr class="divider" >';
        publishedPost.insertAdjacentHTML("afterend", newOption);
    };
    
    const publishPost = () => {
        let currentPost = document.getElementById("post-thread_null-id_" + _postId);
        let referenceElement = document.getElementById("publishButton");
        let newPost =
            '<textarea class="userPost" id="post-thread_null-id_' + (_postId + 1) +
            '" maxLength="140" placeholder="Write something..." ></textarea>';
        currentPost.readOnly = true;
        currentPost.style.backgroundColor = "#090F18";
        currentPost.style.borderWidth = "0.1rem";
        currentPost.style.borderColor = "rgb(0, 183, 255)";
        referenceElement.insertAdjacentHTML("beforebegin", newPost);
        _postId += 1;
        _addConvertToThreadOption(currentPost);
    };

    let createPostFromThread = () => {
        alert("Creating Thread!");
    };

    return {
        publishPost: publishPost,
        createPostFromThread: createPostFromThread
    }

})();