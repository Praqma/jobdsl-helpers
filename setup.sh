docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Action - awaiting feed-back" -c 6EB82C -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Action - needs grooming"     -c 009800 -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Prio 1 - must have"          -c E83D0F -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Prio 2 - should have"        -c EB6420 -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Prio 3 - could have"         -c E8850F -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Prio 4 - won't have"         -c E8A80F -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Size 0 - Briefing"           -c C7DEF8 -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Size 1 - small"              -c 20B4E5 -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Size 2 - medium"             -c 208FE5 -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Size 3 - large"              -c 0052CC -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Size 4 - too big"            -c 100B6B -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Status - duplicate"          -c 111111 -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Status - workable"           -c EDEDED -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Status - in progress"        -c EDEDED -- Praqma/jobdsl-helpers
docker run -i -t --rm -v $HOME:/home/ghi antonmry/ghi-docker label "Status - up Next"            -c EEEEEE -- Praqma/jobdsl-helpers