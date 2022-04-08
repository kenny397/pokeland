/**
 * PJT Ⅰ - 과제 3 테스트 코드 작성
 * @dev NFT mint, transfer, and compare URI 
 */

const NftCreator = artifacts.require("SsafyNFT");

contract("NftCreator", async(accounts) => {
    
    it("NFT mint, transfer, and compare URI", async () => {
        let sender;
        let receiver;
        let tokenURI2;
        const account_one = accounts[0];
        const account_two = accounts[1];
        let testcase = "tes2t";
        let app=await NftCreator.new();
            
        tokenURI2=await app.create(account_one,testcase);
        tokenId= await app.test();
        sender=await app.ownerOf(tokenId);
        await app.transferFrom(account_one,account_two,tokenId);
        receiver=await app.ownerOf(tokenId);
        console.log(tokenURI2);
        console.log(await app.test());
            assert.equal(sender, account_one, "NFT Mint Failed");
            assert.equal(receiver, account_two, "NFT Transfer Failed.");
            assert.equal(testcase, await app.tokenURI(tokenId), "Wrong Token Id or URI.")
        
        



        // TODO
        // 다음이 반드시 테스트되어야 합니다.
        // assert.equal(sender, owner, "NFT Mint Failed");
        // assert.equal(receiver, owner, "NFTh Transfer Failed.");
        // assert.equal(tokenURI, tokenURIFetced, "Wrong Token Id or URI.")
    });

});