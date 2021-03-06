package ch.mibex.bamboo.plandsl.dsl.scm

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'], callSuper = true)
@ToString(includeFields=true)
class Scm extends ScmType  {
    private List<ScmType> scms = []

    Scm(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // for tests
    protected Scm() {
    }

    /**
     * A Bitbucket Cloud repository.
     */
    void bitbucketCloud(String name,
                        @DelegatesTo(value = ScmBitbucketCloud, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, name, ScmBitbucketCloud)
    }

    /**
     * A Bitbucket Cloud repository.
     */
    void bitbucketCloud(Map<String, String> params,
                        @DelegatesTo(value = ScmBitbucketCloud, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, params['name'], ScmBitbucketCloud)
    }

    /**
     * A Bitbucket Server repository.
     */
    void bitbucketServer(String name,
                         @DelegatesTo(value = ScmBitbucketServer, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, name, ScmBitbucketServer)
    }

    /**
     * A Bitbucket Server repository.
     */
    void bitbucketServer(Map<String, String> params,
                         @DelegatesTo(value = ScmBitbucketServer, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, params['name'], ScmBitbucketServer)
    }

    /**
     * A Git repository.
     */
    void git(String name, @DelegatesTo(value = ScmGit, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, name, ScmGit)
    }

    /**
     * A Git repository.
     */
    void git(Map<String, String> params,
             @DelegatesTo(value = ScmGit, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, params['name'], ScmGit)
    }

    /**
     * A GitHub repository.
     */
    void github(String name,
                @DelegatesTo(value = ScmGithub, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, name, ScmGithub)
    }

    /**
     * A GitHub repository.
     */
    void github(Map<String, String> params,
                @DelegatesTo(value = ScmGithub, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, params['name'], ScmGithub)
    }

    /**
     * A subversion repository.
     */
    void subversion(String name,
                    @DelegatesTo(value = ScmSubversion, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, name, ScmSubversion)
    }

    /**
     * A subversion repository.
     */
    void subversion(Map<String, String> params,
                    @DelegatesTo(value = ScmSubversion, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, params['name'], ScmSubversion)
    }

    /**
     * A Mercurial repository.
     */
    void mercurial(String name,
                   @DelegatesTo(value = ScmMercurial, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, name, ScmMercurial)
    }

    /**
     * A Mercurial repository.
     */
    void mercurial(Map<String, String> params,
                   @DelegatesTo(value = ScmMercurial, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, params['name'], ScmMercurial)
    }

    /**
     * A CVS repository.
     */
    void cvs(String name,
             @DelegatesTo(value = ScmCvs, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, name, ScmCvs)
    }

    /**
     * A CVS repository.
     */
    void cvs(Map<String, String> params,
             @DelegatesTo(value = ScmCvs, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, params['name'], ScmCvs)
    }

    /**
     * A Perforce repository.
     */
    void perforce(String name,
                  @DelegatesTo(value = ScmPerforce, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, name, ScmPerforce)
    }

    /**
     * A Perforce repository.
     */
    void perforce(Map<String, String> params,
                  @DelegatesTo(value = ScmPerforce, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        handleScm(closure, params['name'], ScmPerforce)
    }

    /**
     * A Bamboo linked repository.
     */
    void linkedRepository(String name) {
        bambooFacade.requireLinkedRepository(name)
        def linkedRepo = new ScmLinkedRepository(bambooFacade)
        linkedRepo.name = name
        scms << linkedRepo
    }

    /**
     * A Bamboo linked repository.
     */
    void linkedRepository(Map<String, String> params) {
        bambooFacade.requireLinkedRepository(params['name'])
        def linkedRepo = new ScmLinkedRepository(bambooFacade)
        linkedRepo.name = params['name']
        scms << linkedRepo
    }

    /**
     * A custom repository not natively supported.
     *
     * @params params The mandatory parameters for this repository. Only "pluginKey" and "name" are expected.
     */
    void custom(Map<String, String> params,
                @DelegatesTo(value = ScmCustom, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        def repo = new ScmCustom(bambooFacade, params['pluginKey'], params['name'])
        DslScriptHelper.execute(closure, repo)
        scms << repo
    }

    private ScmType handleScm(Closure closure, String displayName, Class<? extends ScmType> clazz) {
        def scm = clazz.newInstance(bambooFacade)
        scm.name = displayName
        DslScriptHelper.execute(closure, scm)
        scms << scm
        scm
    }

}
