echo "Building $TRAVIS_BRANCH"
echo "Commit: $TRAVIS_COMMIT"
git show $TRAVIS_COMMIT
git fetch
git checkout origin/master
git merge --squash $TRAVIS_COMMIT
