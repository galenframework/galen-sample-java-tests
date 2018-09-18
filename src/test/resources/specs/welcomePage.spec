@import common.spec

@objects
    welcome-block       css     .jumbotron
    greeting            css     #welcome-page h1
    text-block-*        css     #welcome-page p
    login-button        css     #welcome-page .button-login



= Content =
    @on *
        text-block-1, login-button, text-block-3:
            inside welcome-block ~30px left

        greeting:
            above text-block-1 10 to 50 px
            inside welcome-block ~ 30px left

        text-block-1:
            height > 20px
            above login-button 60 to 130 px

        login-button:
            height ~ 45px
            text is "Login"
            above text-block-3 -70 to 0px


    @on desktop
        greeting:
            height ~ 69px
            inside welcome-block ~ 68 px top

        login-button:
            width ~ 78px


    @on tablet
        greeting:
            height ~ 39px
            inside welcome-block ~ 50 px top

        login-button:
            width ~ 78px

    @on mobile
        greeting:
            height ~ 78px
            inside welcome-block ~ 50 px top

        login-button:
            inside welcome-block ~ 30px left right
