echo "Building $TRAVIS_BRANCH"
echo "Commit: $TRAVIS_COMMIT"
git fetch origin +refs/heads/*:refs/remotes/origin/*
git branch -a
git checkout origin/$TARGET_BRANCH
git merge --ff-only $TRAVIS_COMMIT
