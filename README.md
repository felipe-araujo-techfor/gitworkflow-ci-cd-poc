- MR - 3 avaliadoras
    - Test
- Build - em dev
- Docker - label short_hash commit
- Kubernetes - Subs var ambiente
- Deploy



Github Actions 

pq ele sabe o hash do commit, pelas env vars que tem no workflow

então a ideia é que a gente padronize uma pasta de ci-cd nos projetos, que vai conter o dockerfile do projeto (edited) 

dado o padrão, o workflow do GA sabe onde procurar um dockerfile pra dar docker build

e consequentemente docker image tag com o SHORT_SHA

e, por fim, subir num registro de imagens, então vai precisar ter credenciais salvas pra acessar o ECR por exemplo

os steps poderiam ser algo do tipo:
- docker build --build-arg PROFILE=${PROFILE} -t ${DOCKER_REPO}/${PROJECT_NAMESPACE}-${CI_COMMIT_REF_NAME}/${CI_PROJECT_NAME} .
- docker tag ${DOCKER_REPO}/${PROJECT_NAMESPACE}-${CI_COMMIT_REF_NAME}/${CI_PROJECT_NAME} ${ECR}/${DOCKER_REPO}/${PROJECT_NAMESPACE}-${CI_COMMIT_REF_NAME}/${CI_PROJECT_NAME}:${CI_COMMIT_REF_NAME}-${CI_COMMIT_SHORT_SHA}
- docker push ${ECR}/${DOCKER_REPO}/${PROJECT_NAMESPACE}-${CI_COMMIT_REF_NAME}/${CI_PROJECT_NAME}:${CI_COMMIT_REF_NAME}-${CI_COMMIT_SHORT_SHA}
