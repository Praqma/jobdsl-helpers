echo "Building $TRAVIS_BRANCH"
echo "Commit: $TRAVIS_COMMIT"
git fetch origin +refs/heads/*:refs/remotes/origin/*
git branch -a
git checkout origin/master
git merge --squash $TRAVIS_COMMIT
